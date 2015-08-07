package rbccps.iot.ncap.SNaaS.CP.TEDS.LocalCache;

/*
 *
 * $RCSfile: TEDSDataStore.java $
 *
 * Copyright (c) 2015, RBCCPS, IISc Bangalore.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *    -	Redistributions of source code must retain the above
 *      copyright notice, this list of conditions and the following
 *      disclaimer.
 *    -	Redistributions in binary form must reproduce the above
 *      copyright notice, this list of conditions and the following
 *      disclaimer in the documentation and/or other materials provided
 *      with the distribution.
 *    -	Neither the name of RBCCPS, IISc Bangalore nor the names
 *      of its contributors may be used to endorse or promote products
 *      derived from this software without specific prior written
 *      permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import rbccps.iot.ncap.SNaaS.CP.TEDS.LDAP.callLDAPAsyncTask;


public class TEDSDataStore {

    // Database fields
    private static SQLiteDatabase database;
    private static SQLite dbHelper;
    private static String[] allColumns = { SQLite.COLUMN_ID,
            SQLite.COLUMN_KEY, SQLite.COLUMN_VALUE };

    public static boolean open = false;
    public static boolean create = false;
    public static boolean hit = false;

    public TEDSDataStore(Context context) {
        Log.e("TEDSDataStore","TEDSDataStore context");
        if(!open && !create){
            dbHelper = new SQLite(context);
            open = true;
            create = true;
            open();
            create(context);}

           if(!hit){
            hit = true;
        createTEDS(callLDAPAsyncTask.uuid, callLDAPAsyncTask.binaryTEDS);}
    }

    private void create(Context context) {
        SQLite sql = new SQLite(context);
        sql.onCreate(database);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
     }

    public void close() {
        dbHelper.close();
    }

    public void createTEDS(String uuid, String teds) {

        ContentValues values = new ContentValues();
        values.put(SQLite.COLUMN_KEY, uuid);
        values.put(SQLite.COLUMN_VALUE, teds);

        long insertId = database.insert(SQLite.TABLE_NAME, null,
                values);
        Cursor cursor = database.query(SQLite.TABLE_NAME,
                allColumns, SQLite.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Log.d("TAG", DatabaseUtils.dumpCursorToString(cursor));
        cursor.close();
     }

}
