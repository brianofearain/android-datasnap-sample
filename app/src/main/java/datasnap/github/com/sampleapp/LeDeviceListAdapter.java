package datasnap.github.com.sampleapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.estimote.sdk.Beacon;

import java.util.ArrayList;
import java.util.Collection;


public class LeDeviceListAdapter extends BaseAdapter {

    // an adapter for beacon data

    private ArrayList<Beacon> beacons;
    private LayoutInflater inflater;

    public LeDeviceListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.beacons = new ArrayList<Beacon>();
    }

    public void replaceWith(Collection<Beacon> newBeacons) {
        this.beacons.clear();
        this.beacons.addAll(newBeacons);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beacons.size();
    }

    @Override
    public Beacon getItem(int position) {
        return beacons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflateIfRequired(view, position, parent);
        bind(getItem(position), view);
        return view;
    }

    private void bind(Beacon beaconn, View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        //    holder.macTextView.setText(String.format("MAC: %s (%.2fm)", beaconn.getMacAddress(), Utils.computeAccuracy(beaconn)));
        holder.majorTextView.setText("Major: " + beaconn.getMajor());
        holder.minorTextView.setText("Minor: " + beaconn.getMinor());
        holder.measuredPowerTextView.setText("MPower: " + beaconn.getMeasuredPower());
        holder.rssiTextView.setText("RSSI: " + beaconn.getRssi());

    }

    private View inflateIfRequired(View view, int position, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_main, null);
            view.setTag(new ViewHolder(view));
        }
        return view;
    }

    static class ViewHolder {
        final TextView beaconSightEvent;
        //  final TextView macTextView;
        final TextView majorTextView;
        final TextView minorTextView;
        final TextView measuredPowerTextView;
        final TextView rssiTextView;

        ViewHolder(View view) {
            beaconSightEvent = (TextView) view.findViewWithTag("event");
            //   macTextView = (TextView) view.findViewWithTag("mac");
            majorTextView = (TextView) view.findViewWithTag("major");
            minorTextView = (TextView) view.findViewWithTag("minor");
            measuredPowerTextView = (TextView) view.findViewWithTag("mpower");
            rssiTextView = (TextView) view.findViewWithTag("rssi");
        }
    }
}
