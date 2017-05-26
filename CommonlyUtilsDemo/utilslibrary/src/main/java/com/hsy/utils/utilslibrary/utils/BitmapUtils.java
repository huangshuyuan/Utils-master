package com.hsy.utils.utilslibrary.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;

/**
 * 图片压缩工具类
 * <p>
 * Created by 黄淑媛 on 2017.5.23.
 */
public class BitmapUtils {
    class BitmapTask extends AsyncTask<Void, Void, File> {
        private File file;
        private File newFile;

        public BitmapTask(File file, File newFile) {
            this.file = file;
            this.newFile = newFile;
        }

        @Override
        protected File doInBackground(Void... voids) {
            return compress(file, newFile);
        }

        @Override
        protected void onPostExecute(File f) {
            super.onPostExecute(f);
            if (EmptyUtils.isNotEmpty(listener)) {
                listener.onSuccess(f, file);
            }
        }
    }

    private File compress(File file, File newFile) {
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        try {
            FileOutputStream fos = new FileOutputStream(newFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, fos);
            fos.flush();
            fos.close();
            bitmap.recycle();
            bitmap = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return newFile;
    }

    public void compress(File file, File newFile, CompressListener listener) {
        this.listener = listener;
        new BitmapTask(file, newFile).execute();
    }

    private CompressListener listener;

    public interface CompressListener {
        public void onSuccess(File file, File oldFile);
    }

    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2 - 5;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2 - 5;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }

        Bitmap output = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right,
                (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top,
                (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst_left + 15, dst_top + 15,
                dst_right - 20, dst_bottom - 20);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }

    public static Bitmap genBitmap(File file, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_4444; // Ĭ����Bitmap.Config.ARGB_8888

        if (0 != reqWidth && 0 != reqHeight) {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            int inSampleSize = calculateInSampleSize(options, reqWidth,
                    reqHeight);
            options.inJustDecodeBounds = false;
            if (0 != inSampleSize && 1 != inSampleSize) {
                options.inSampleSize = inSampleSize;
            }
        }
        return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        final int width = options.outWidth;
        final int height = options.outHeight;
        int inSampleSize = 1;
        if (width > reqWidth || height > reqHeight) {
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            inSampleSize = widthRatio < heightRatio ? widthRatio : heightRatio;
        }
        return inSampleSize;
    }

}
