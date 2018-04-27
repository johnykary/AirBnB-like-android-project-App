package gr.uoa.di.ecommerce.myairbnb;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

class RoomFamousAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> names;
    private ArrayList<String> images;
    private ArrayList<String> prices;
    private ArrayList<String> addresses;
    private ArrayList<String> rates;

    RoomFamousAdapter(Context context, ArrayList<String> names, ArrayList<String> images, ArrayList<String> prices, ArrayList<String> addresses, ArrayList<String> rates) {
        this.context = context;
        this.names = names;
        this.images = images;
        this.prices = prices;
        this.addresses = addresses;
        this.rates = rates;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public String getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.roomview, null);
        }

        TextView roomName = (TextView) convertView.findViewById(R.id.RoomName);
        roomName.setTag(position);
        ImageView roomImage = (ImageView) convertView.findViewById(R.id.RoomImage);
        roomImage.setTag(position);
        TextView roomDescription = (TextView) convertView.findViewById(R.id.RoomDescription);
        roomDescription.setTag(position);
        RatingBar roomRate = (RatingBar) convertView.findViewById(R.id.RatingBar);
        roomRate.setTag(position);

        Bitmap bitmap;
        if (!images.get(position).equals("")) {
            byte[] roomImage_byteArray = Base64.decode(images.get(position), Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(roomImage_byteArray, 0, roomImage_byteArray.length);
        } else {
            bitmap = null;
        }

        roomName.setText(names.get(position));
        roomImage.setImageBitmap(bitmap);
        roomDescription.setText(context.getResources().getString(R.string.pricePerDay) +" "+ prices.get(position) +"\n\n" +context.getResources().getString(R.string.address) +" "+ addresses.get(position));
        roomRate.setRating(Float.parseFloat(rates.get(position)));
        return convertView;
    }
}
