package br.com.borges.moises.expensetracker.dashboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.borges.moises.expensetracker.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Moisés on 21/02/2016.
 */
public class DashboardFragment extends Fragment implements DashboardContract.View {

    private DashboardContract.UserActionsListener mUserActionsListener;
    private OverviewCardsAdapter mOverviewCardsAdapter;


    @Bind(R.id.cards_list_recycler_view)
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);

        mOverviewCardsAdapter = new OverviewCardsAdapter(new ArrayList<OverviewCard>(0), getFragmentManager());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mOverviewCardsAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUserActionsListener = new DashboardPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserActionsListener.loadCards();
    }

    public static Fragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public void showOverviewCards(List<OverviewCard> overviewCards) {
        mOverviewCardsAdapter.replaceData(overviewCards);
    }

    public static class OverviewCardsAdapter extends RecyclerView.Adapter<OverviewCardViewHolder> {

        private final FragmentManager mFragmentManager;
        private List<OverviewCard> mOverviewCards;

        public OverviewCardsAdapter(@NonNull List<OverviewCard> overviewCards,
                                    @NonNull FragmentManager fragmentManager) {
            mOverviewCards = overviewCards;
            mFragmentManager = fragmentManager;
        }

        public void replaceData(@NonNull List<OverviewCard> overviewCards) {
            mOverviewCards = overviewCards;
            notifyDataSetChanged();
        }

        @Override
        public OverviewCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.overview_card_item, parent, false);
            OverviewCardViewHolder viewHolder = new OverviewCardViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(OverviewCardViewHolder holder, int position) {
            OverviewCard overviewCard = mOverviewCards.get(position);
            holder.bindOverviewCard(overviewCard, mFragmentManager);
        }

        @Override
        public int getItemCount() {
            return mOverviewCards.size();
        }

    }

    public static class OverviewCardViewHolder extends RecyclerView.ViewHolder {

        public OverviewCardViewHolder(View itemView) {
            super(itemView);
        }

        public void bindOverviewCard(OverviewCard overviewCard, FragmentManager fragmentManager) {
            Fragment fragment = overviewCard.getFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.card_info_framelayout, fragment)
                    .commit();
        }
    }
}
