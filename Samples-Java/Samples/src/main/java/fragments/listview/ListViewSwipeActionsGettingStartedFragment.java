package fragments.listview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.telerik.android.sdk.R;
import com.telerik.widget.list.ListViewAdapter;
import com.telerik.widget.list.ListViewHolder;
import com.telerik.widget.list.RadListView;
import com.telerik.widget.list.SwipeActionsBehavior;

import java.util.ArrayList;
import java.util.List;

import activities.ExampleFragment;

/**
 * Created by ginev on 2/20/2015.
 */
public class ListViewSwipeActionsGettingStartedFragment extends Fragment implements ExampleFragment {

    private RadListView listView;
    private SwipeActionsBehavior sab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list_view_example, container, false);

        this.listView = (RadListView) rootView.findViewById(R.id.listView);

        ArrayList<EmailMessage> dataSource = new ArrayList<>();

        EmailMessage message = new EmailMessage();
        message.title = "Tech news";
        message.content = "Here is your daily LinkedIn feed with news about .NET, Java, iOS and more...";
        dataSource.add(message);

        message = new EmailMessage();
        message.title = "Awaiting Payment";
        message.content = "Monthly bills summary: water supply, electricity, earth gas...";
        dataSource.add(message);

        message = new EmailMessage();
        message.title = "Greetings from Hawai";
        message.content = "Hey Betty, we've just arrived! What a flight!...";
        dataSource.add(message);

        this.listView.setAdapter(new MyListViewAdapter(dataSource));

        // >> swipe-actions-getting-started

        this.sab = new SwipeActionsBehavior();
        this.sab.addListener(new SwipeActionsBehavior.SwipeActionsListener() {

            @Override
            public void onSwipeStarted(SwipeActionsBehavior.SwipeActionEvent swipeActionEvent) {
                // Fired when the user starts swiping an item.
            }

            @Override
            public void onSwipeProgressChanged(SwipeActionsBehavior.SwipeActionEvent swipeActionEvent) {
                // Continuously fired while the user swipes an item.
            }

            @Override
            public void onSwipeEnded(SwipeActionsBehavior.SwipeActionEvent swipeActionEvent) {
                // Fired when the user releases the item being swiped.
            }

            @Override
            public void onExecuteFinished(SwipeActionsBehavior.SwipeActionEvent swipeActionEvent) {
                // Fired when the swipe-execute procedure has ended, i.e. the item being swiped is at
                // its original position.
            }
        });

        this.listView.addBehavior(this.sab);

        // << swipe-actions-getting-started

        return rootView;
    }

    @Override
    public String title() {
        return "Swipe actions: getting started";
    }

    class EmailMessage {
        public String title;
        public String content;
    }

    class MyListViewAdapter extends ListViewAdapter {

        public MyListViewAdapter(List items) {
            super(items);
        }

        @Override
        public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.example_list_view_item_swipe_layout, parent, false);
            MyCustomViewHolder customHolder = new MyCustomViewHolder(itemView);
            return customHolder;
        }

        @Override
        public void onBindViewHolder(ListViewHolder holder, int position) {
            MyCustomViewHolder customVH = (MyCustomViewHolder) holder;
            EmailMessage message = (EmailMessage) this.getItem(position);
            customVH.txtTitle.setText(message.title);
            customVH.txtContent.setText(message.content);
        }

        @Override
        public ListViewHolder onCreateSwipeContentHolder(ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View swipeContentView = inflater.inflate(R.layout.example_list_view_swipe_actions_gs_swipe_content, viewGroup, false);
            MySwipeContentViewHolder vh = new MySwipeContentViewHolder(swipeContentView);
            return vh;
        }

        @Override
        public void onBindSwipeContentHolder(final ListViewHolder viewHolder, final int position) {
            final EmailMessage currentMessage = (EmailMessage)getItem(position);
            MySwipeContentViewHolder swipeContentHolder = (MySwipeContentViewHolder)viewHolder;
        }
    }

    class MyCustomViewHolder extends ListViewHolder {

        public TextView txtTitle;
        public TextView txtContent;

        public MyCustomViewHolder(View itemView) {
            super(itemView);
            this.txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            this.txtContent = (TextView) itemView.findViewById(R.id.txtContent);
        }
    }

    class MySwipeContentViewHolder extends ListViewHolder {

        public Button action1;
        public Button action2;

        public MySwipeContentViewHolder(final View itemView) {
            super(itemView);
            this.action1 = (Button) itemView.findViewById(R.id.btnAction1);
            this.action2 = (Button) itemView.findViewById(R.id.btnAction2);
        }
    }
}
