package aim.helmi.bootcamp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.List;

import aim.helmi.bootcamp.R;
import aim.helmi.bootcamp.model.CommentModel;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private Context context;
    private List<CommentModel> mData;

    public CommentAdapter(Context context, List<CommentModel> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.bind(mData.get(i));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProfile;
        TextView user;
        TextView message;
        TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.tvUsername);
            message= itemView.findViewById(R.id.tvMessage);
            date = itemView.findViewById(R.id.tvDate);
            imgProfile =itemView.findViewById(R.id.imgProfile);

        }

        public void bind(CommentModel commentModel) {
            user.setText(commentModel.getUsername());
            message.setText(commentModel.getComment());
            date.setText(commentModel.getDate());

            String url ="https://a.wattpad.com/cover/157520716-352-k426060.jpg";

            Glide.with(itemView.getContext())
                    .load(url)
                    .transform(new MultiTransformation<>(new CenterCrop(), new RoundedCorners(20)))
                    .into(imgProfile);
        }
    }
}
