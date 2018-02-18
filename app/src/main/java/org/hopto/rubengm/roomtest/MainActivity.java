package org.hopto.rubengm.roomtest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import org.hopto.rubengm.roomtest.databinding.ActivityMainBinding;
import org.hopto.rubengm.roomtest.db.Datamanager;
import org.hopto.rubengm.roomtest.db.objects.Item;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		binding.list.setAdapter(new ItemAdapter());
		binding.add.setOnClickListener(this::add);
		binding.empty.setOnClickListener(this::empty);
		Datamanager.getLiveItems(this, liveItems -> liveItems.observe(this, items -> {
			boolean autoScroll = ((LinearLayoutManager) binding.list.getLayoutManager()).findFirstCompletelyVisibleItemPosition() == 0;
			((ItemAdapter) binding.list.getAdapter()).setItems(items);
			if(autoScroll) binding.list.scrollToPosition(0);
		}));
	}

	private void add(View view) {
		Datamanager.insertItems(view.getContext(), new Item("Title", "Description"));
	}

	private void empty(View view) {
		Datamanager.empty(view.getContext());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Datamanager.markAllAsRead(this);
	}
}