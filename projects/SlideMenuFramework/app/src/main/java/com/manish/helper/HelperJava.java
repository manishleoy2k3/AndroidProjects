package com.manish.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.DisplayMetrics;

/**
 * Created by manish on 1/16/2015.
 */
public class HelperJava {

    /**Method to get round shaped bitmap. Mostly used for profile pictures
     *
     *     @param scaleBitmapImage
     *                      The source bitmap which has to converted to round shape
     *     @param context
     *                      The context
     *     @param targetWidthPixels
     *                      The width required for the target or returned  bitmap
     *     @param targetHeightPixels
     *                      The height required for the target or returned  bitmap
     *     @return
     *            round shaped bitmap
     */
    public static Bitmap getRoundedShape(Bitmap scaleBitmapImage,Context context,int targetWidthPixels,int targetHeightPixels) {

        Bitmap targetBitmap = Bitmap.createBitmap(targetWidthPixels,
                targetHeightPixels,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidthPixels - 1) / 2,
                ((float) targetHeightPixels - 1) / 2,
                (Math.min(((float) targetWidthPixels),
                        ((float) targetHeightPixels)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidthPixels, targetHeightPixels), null);
        return targetBitmap;
    }


    /***Method to convert dp tp pixel value
     *
     * @param dp The value to convert into pixels
     * @param context the context
     * @return pixels corresponding to dp value
     */
    public int dpToPx(int dp,Context context) {
	    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
	    return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
	}
}
