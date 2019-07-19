package dell.example.com.quizz.TopicAdapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import dell.example.com.quizz.R;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.TopicCustomViewHolder>{

    Context mContext;
    ArrayList<String> mArrayList;
    ArrayList<GradientDrawable> mGradientDrawable;

  public   TopicsAdapter(Context context, ArrayList<String> arrayList){
        mContext=context;
        mArrayList=arrayList;
        mGradientDrawable=new ArrayList<>();
      fillGradientList(mContext);
    }

    public void addTopic(String topicName)
    {
        mArrayList.add(topicName);
    }

    @Override
    public TopicCustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.topic_item_list_view,viewGroup,false);
         return new TopicCustomViewHolder(view);
    }

    private void fillGradientList(Context context)
    {
        mGradientDrawable.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_1_start),ContextCompat.getColor(context,R.color.gradient_1_end)));
        mGradientDrawable.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_2_start),ContextCompat.getColor(context,R.color.gradient_2_end)));
        mGradientDrawable.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_3_start),ContextCompat.getColor(context,R.color.gradient_3_end)));
        mGradientDrawable.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_4_start),ContextCompat.getColor(context,R.color.gradient_4_end)));
    }


    private GradientDrawable getTempGradientDrawable(int startColor,int endColor)
    {
        GradientDrawable drawable=new GradientDrawable(GradientDrawable.Orientation.BL_TR,new int[]{startColor,endColor});
        drawable.setDither(true);
        drawable.setGradientCenter(drawable.getIntrinsicWidth()/8,drawable.getIntrinsicHeight()/2);
        drawable.setCornerRadius(20);
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        drawable.setUseLevel(true);
        return  drawable;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull TopicCustomViewHolder topicCustomViewHolder, int i) {

      String topicName=mArrayList.get(i);
        topicCustomViewHolder.textView.setText(topicName);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN)
        {
            topicCustomViewHolder.mTopicRealtiveLayout.setBackground(mGradientDrawable.get(i % 4));
        }else {
            topicCustomViewHolder.mTopicRealtiveLayout.setBackgroundDrawable(mGradientDrawable.get(i % 4));
        }

        topicCustomViewHolder.imageView.setImageResource(R.drawable.ic_menu);

    }


    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class  TopicCustomViewHolder extends RecyclerView.ViewHolder{

      private ImageView imageView;
      private TextView textView;
      private RelativeLayout mTopicRealtiveLayout;


        public TopicCustomViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.topic_text_view);
            imageView=itemView.findViewById(R.id.topic_image_view);
            mTopicRealtiveLayout=itemView.findViewById(R.id.topicRelativeLayout);
        }
    }

}