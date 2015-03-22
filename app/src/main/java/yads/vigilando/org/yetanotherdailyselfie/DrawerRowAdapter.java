package yads.vigilando.org.yetanotherdailyselfie;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DrawerRowAdapter extends BaseAdapter {

	private ArrayList<DrawerRow> list = new ArrayList<DrawerRow>();
	private static LayoutInflater inflater = null;
	private Context mContext;

	public DrawerRowAdapter(Context context) {
		mContext = context;
		inflater = LayoutInflater.from(mContext);
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View newView = convertView;
		ViewHolder holder;

		DrawerRow curr = list.get(position);

		if (null == convertView) {
			holder = new ViewHolder();
			newView = inflater
					.inflate(R.layout.drawer_row, parent, false);
			holder.icon = (ImageView) newView.findViewById(R.id.rowIcon);
			holder.title = (TextView) newView.findViewById(R.id.rowTitle);
			newView.setTag(holder);

		} else {
			holder = (ViewHolder) newView.getTag();
		}

		holder.icon.setImageDrawable(mContext.getResources().getDrawable((curr.getIconId())));
		holder.title.setText(curr.getTitle());

		return newView;
	}

	static class ViewHolder {
		ImageView icon;
		TextView title;
	}

	public void add(DrawerRow listItem) {
		list.add(listItem);
		notifyDataSetChanged();
	}

	public ArrayList<DrawerRow> getList() {
		return list;
	}

	public void removeAllViews() {
		list.clear();
		this.notifyDataSetChanged();
	}
}
