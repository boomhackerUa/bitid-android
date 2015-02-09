/**
 * Copyright 2014 Skubit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.skubit.bitid.activities;

import com.coinbase.zxing.client.android.Intents;
import com.skubit.bitid.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startBarcodeScan() {
        Intent intent = new Intent(this, com.coinbase.zxing.client.android.CaptureActivity.class);
        intent.setAction(Intents.Scan.ACTION);
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        Intent loginIntent = AuthenticationActivity.newInstance(data.getStringExtra("SCAN_RESULT"));
        startActivity(loginIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int order = item.getOrder();
         if (order == 1) {
             startBarcodeScan();
        } else if (order == 2) {
            Intent i = new Intent();
            i.setClass(this, ImportActivity.class);
            startActivity(i);
        } else if (order == 3) {
            Intent i = new Intent();
            i.setClass(this, ExportActivity.class);
            startActivity(i);
        } else if (order == 4) {
            Intent i = new Intent();
            i.setClass(this, DisplayLicensesActivity.class);
            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }
}
