package p8.group3.ida.aau.p8_group3.Presenter;

import android.content.Context;
import android.graphics.Typeface;

public class FontManager {

    public static final String ROOT = "fonts/",
            KGCorneroftheSky = ROOT + "KGCorneroftheSky.ttf";

    public static Typeface getTypeface(Context context, String font){
        return Typeface.createFromAsset(context.getAssets(), font);
    }

    // for other fonts use this command: yourTextView.setTypeface(FontManager.getTypeface(FontManager.YOURFONT));


}
