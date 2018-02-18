package org.hopto.rubengm.roomtest;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hopto.rubengm.roomtest.databinding.RowItemBinding;
import org.hopto.rubengm.roomtest.db.Datamanager;
import org.hopto.rubengm.roomtest.db.objects.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Creado por ruben el 18/02/2018.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
	private final List<Item> mItems;

	public ItemAdapter() {
		this.mItems = new ArrayList<>();
	}

	public void setItems(List<Item> items) {
		ItemDiffCallback diffCallback = new ItemDiffCallback(mItems, items);
		DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

		this.mItems.clear();
		this.mItems.addAll(items);

		diffResult.dispatchUpdatesTo(this);
	}

	private class ItemDiffCallback extends DiffUtil.Callback {
		private List<Item> oldList;
		private List<Item> newList;

		private ItemDiffCallback(List<Item> oldList, List<Item> newList) {
			this.oldList = oldList;
			this.newList = newList;
		}

		@Override
		public int getOldListSize() {
			return oldList.size();
		}

		@Override
		public int getNewListSize() {
			return newList.size();
		}

		@Override
		public boolean areItemsTheSame(int oldIndex, int newIndex) {
			return oldList.get(oldIndex).getId() == newList.get(newIndex).getId();
		}

		@Override
		public boolean areContentsTheSame(int oldIndex, int newIndex) {
			return oldList.get(oldIndex).equals(newList.get(newIndex));
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		RowItemBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_item, parent, false);
		return new ViewHolder(mBinding);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		holder.mItem = mItems.get(position);
		holder.b.title.setText(String.format(Locale.getDefault(), "%s - ID: %d", holder.mItem.getTitle(), holder.mItem.getId()));
		holder.b.description.setText(holder.mItem.getDescription());
		holder.b.newNotification.setVisibility(holder.mItem.isNew() ? View.VISIBLE : View.INVISIBLE);
		holder.b.delete.setOnClickListener(v -> Datamanager.deleteItems(v.getContext(), holder.mItem));
		holder.b.card.setOnClickListener(v -> Datamanager.setRead(v.getContext(), holder.mItem.getId()));
	}

	@Override
	public int getItemCount() {
		return mItems.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		final RowItemBinding b;
		Item mItem;

		ViewHolder(RowItemBinding view) {
			super(view.getRoot());
			b = view;
		}
	}
}