package p8.group3.ida.aau.p8_group3;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by christosmentzelos on 06/04/2017.
 */

public class FontManager {

    public static final String ROOT = "fonts/",
            FONTAWESOME = ROOT + "fontawesome-webfont.ttf";

    public static Typeface getTypeface(Context context, String font){
        return Typeface.createFromAsset(context.getAssets(), font);
    }

    // for other fonts use this command: yourTextView.setTypeface(FontManager.getTypeface(FontManager.YOURFONT));


}
