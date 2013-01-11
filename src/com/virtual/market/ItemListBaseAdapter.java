package com.virtual.market;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemListBaseAdapter extends BaseAdapter {
	private static ArrayList<ItemDetails> itemDetailsArrayList;
	
	private LayoutInflater l_Inflater;

	public ItemListBaseAdapter(Context context, ArrayList<ItemDetails> results) {
		itemDetailsArrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return itemDetailsArrayList.size();
	}

	public Object getItem(int position) {
		return itemDetailsArrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.item_details_view, null);
			holder = new ViewHolder();
			holder.txt_itemName = (TextView) convertView.findViewById(R.id.item_name);
			holder.txt_itemDescription = (TextView) convertView.findViewById(R.id.item_description);
			holder.txt_itemPrice = (TextView) convertView.findViewById(R.id.item_price);
			holder.txt_itemAmount=(TextView) convertView.findViewById(R.id.item_amount);
			holder.itemImage = (ImageView) convertView.findViewById(R.id.item_image);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txt_itemName.setText(itemDetailsArrayList.get(position).getName());
		holder.txt_itemDescription.setText(itemDetailsArrayList.get(position).getItemDescription());
		holder.txt_itemPrice.setText(Integer.valueOf(itemDetailsArrayList.get(position).getPrice())+" L.E.");
		holder.txt_itemAmount.setText(itemDetailsArrayList.get(position).getAmount());
		holder.itemImage.setImageBitmap(itemDetailsArrayList.get(position).getBmp());

		return convertView;
	}

	static class ViewHolder {
		TextView txt_itemName;
		TextView txt_itemDescription;
		TextView txt_itemPrice;
		TextView txt_itemAmount;
		ImageView itemImage;
	}
}