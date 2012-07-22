package com.ckarthik17.bestpcconfig;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ConfigTableLayout {
    private final Activity activity;
    private TableLayout uiTableLayout;

    private TableRow.LayoutParams textLayoutWeight1 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
    private TableRow.LayoutParams textLayoutWeight2 = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2);

    public ConfigTableLayout(Activity activity, int configTableId) {
        this.activity = activity;
        this.uiTableLayout = (TableLayout)activity.findViewById(configTableId) ;
    }

    public void updateLayout(Elements tableRows) {
        uiTableLayout.removeAllViews();

        for (Element tableRow : tableRows) {
            TableRow uiTableRow = new TableRow(activity);

            Elements tableData = tableRow.select("td");

            MyTextView typeTextView = new MyTextView(activity);
            typeTextView.setLayoutParams(textLayoutWeight1);
            typeTextView.setText(tableData.get(0).text());
            typeTextView.setPadding(5,2,5,2);

            MyTextView detailTextView = new MyTextView(activity);
            detailTextView.setLayoutParams(textLayoutWeight2);
            detailTextView.setText(tableData.get(1).text());
            detailTextView.setPadding(5,2,5,2);

            MyTextView priceTextView = new MyTextView(activity);
            priceTextView.setLayoutParams(textLayoutWeight1);
            priceTextView.setText(tableData.get(2).text());
            priceTextView.setPadding(5,2,5,2);

            uiTableRow.addView(typeTextView);
            uiTableRow.addView(detailTextView);
            uiTableRow.addView(priceTextView);

            uiTableLayout.addView(uiTableRow);
        }
    }
}
