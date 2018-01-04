package com.example.amrez.habittracker.SqlData;

import android.provider.BaseColumns;
import android.provider.OpenableColumns;

/**
 * Created by amrez on 11/6/2017.
 */

public class Contract {
    public static final class DataEntry implements BaseColumns {
        public final static String TABLE_NAME = "students";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
        public final static String AGE = "age";
        public final static String BIO = "bio";
    }
}
