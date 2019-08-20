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

    private final Context mContext;
    private int mPosition;
    private int mSubPosition;

    @Inject
    public Serializer(final Context context) {
        mContext = context;
    }

    public int writeItems(final List<MediaItem> items, final int position) {
        mPosition = position;
        final List<MediaItem> pack = getPack(items, position);

        deleteTempFileOnExist();

        try (final FileOutputStream fos = mContext.openFileOutput(TEMP_FILE, Context.MODE_PRIVATE);
             final ObjectOutputStream outputStream = new ObjectOutputStream(fos)) {

            outputStream.writeObject(pack);
            outputStream.flush();
            return mSubPosition;
        } catch (final IOException e) {
            e.printStackTrace();
            return position;
        }
    }

    private List<MediaItem> getPack(final List<MediaItem> items, final int position){
        if (items.size() < SERIALIZATION_COUNT) {
            mSubPosition = position;
            return items;
        }

        final MediaItem positionItem = items.get(position);
        final int half = SERIALIZATION_COUNT / 2;
        final int startIndex = (position - half < 0) ? 0 : position - half;
        final int endIndex = (position + half < items.size()) ? position + half : items.size();

        final List<MediaItem> list = new ArrayList<>(items.subList(startIndex, endIndex));
        mSubPosition = list.indexOf(positionItem);

        return list;
    }

    public List<MediaItem> readItems() {
        List<MediaItem> mediaItems;

        try (final FileInputStream fis = mContext.openFileInput(TEMP_FILE);
             final ObjectInputStream inputStream = new ObjectInputStream(fis)) {

            mediaItems = (List<MediaItem>) inputStream.readObject();
            deleteTempFileOnExist();
        } catch (final IOException e) {
            e.printStackTrace();
            mediaItems = new ArrayList<>();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
            mediaItems = new ArrayList<>();
        }
        return mediaItems;

    }

    public void deleteTempFileOnExist() {
        final File filesDir = mContext.getFilesDir();
        final String tempFilePath = String.format(Locale.ENGLISH, "%s/%s",
                filesDir.getPath(), TEMP_FILE);
        final File tempFile = new File(tempFilePath);

        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    public int getActualPosition(final int lastPosition){
        final int step = lastPosition - mSubPosition;
        return mPosition + step;
    }

    public int getSubPosition() {
        return mSubPosition;
    }
}
