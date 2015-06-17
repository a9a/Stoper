package osinska.agnieszka.stooper;

import android.os.SystemClock;
import android.util.Log;

/**
 * Created by Aga on 2015-06-15.
 */
public class Czas {
   private long ms;
   private String data;
   private long nr;
   private String txt;
   private String wynikString;

   public Czas(long ms, String data) {
      this.data = data;
      this.ms = ms;

   }
   public Czas(){};


   public long getMs() {
      return ms;
   }

   public String getData() {
      return data;
   }
   public String rezultatString(){
      String wynik="0:00.000";
      double sek=ms/1000.0;
      int min=0;
      if(sek>=60){
         Log.d("stop2","sek  "+sek);
         min=((int)sek)/60;
         Log.d("stop", "min="+min);
         sek=sek-min*60;
      }
      wynik=""+min+":"+(float)sek;
      if(min==0){
         wynik="00:"+sek;
      }
      wynikString=wynik;
      return wynik;
   }

   public String getTxt() {
      return txt;
   }

   public void setTxt(String txt) {
      this.txt = txt;
   }

   public void setData(String data) {
      this.data = data;
   }

   public void setNr(long nr) {
      this.nr = nr;
   }

   public void setWynikString(String wynikString) {
      this.wynikString = wynikString;
   }

   public long getNr() {
      return nr;
   }

   public String getWynikString() {
      return wynikString;
   }
}
