package com.kara4k.mediagrub.other;


import android.content.Context;

import com.kara4k.mediagrub.model.database.MediaItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class Serializer {

    private static final String TEMP_FILE = "temp_file";
    private static final int SERIALIZATION_COUNT = 500;

    private Context mContext;
    private int mPosition;
    private int mSubPosition;

    @Inject
    public Serializer(Context context) {
        mContext = context;
    }

    public int writeItems(List<MediaItem> items, int position) {
        mPosition = position;
        List<MediaItem> pack = getPack(items, position);

        deleteTempFileOnExist();

        try (FileOutputStream fos = mContext.openFileOutput(TEMP_FILE, Context.MODE_PRIVATE);
             ObjectOutputStream outputStream = new ObjectOutputStream(fos)) {

            outputStream.writeObject(pack);
            outputStream.flush();
            return mSubPosition;
        } catch (IOException e) {
            e.printStackTrace();
            return position;
        }
    }

    private List<MediaItem> getPack(List<MediaItem> items, int position){
        if (items.size() < SERIALIZATION_COUNT) {
            mSubPosition = position;
            return items;
        }

        MediaItem positionItem = items.get(position);
        int half = SERIALIZATION_COUNT / 2;
        int startIndex = (position - half < 0) ? 0 : position - half;
        int endIndex = (position + half < items.size()) ? position + half : items.size();

        List<MediaItem> list = new ArrayList<>(items.subList(startIndex, endIndex));
        mSubPosition = list.indexOf(positionItem);

        return list;
    }

    public List<MediaItem> readItems() {
        List<MediaItem> mediaItems;

        try (FileInputStream fis = mContext.openFileInput(TEMP_FILE);
             ObjectInputStream inputStream = new ObjectInputStream(fis)) {

            mediaItems = (List<MediaItem>) inputStream.readObject();
            deleteTempFileOnExist();
        } catch (IOException e) {
            e.printStackTrace();
            mediaItems = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            mediaItems = new ArrayList<>();
        }
        return mediaItems;

    }

    public void deleteTempFileOnExist() {
        File filesDir = mContext.getFilesDir();
        String tempFilePath = String.format(Locale.ENGLISH, "%s/%s",
                filesDir.getPath(), TEMP_FILE);
        File tempFile = new File(tempFilePath);

        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    public int getActualPosition(int lastPosition){
        int step = lastPosition - mSubPosition;
        return mPosition + step;
    }

    public int getSubPosition() {
        return mSubPosition;
    }
}
