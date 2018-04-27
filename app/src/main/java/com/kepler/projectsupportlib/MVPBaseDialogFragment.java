package com.kepler.projectsupportlib;

import android.content.Context;
import android.view.View;


public abstract class MVPBaseDialogFragment<T extends MVP.BasePresenter, L> extends BaseDialogFragment implements MVP.BaseView {

        protected FragmentCommunicator fragmentCommunicator;
        private static final String TAG = MVPBaseDialogFragment.class.getSimpleName();
        /**
         * The Presenter attached to this View
         */
        protected T presenter;
        private View view;

        /**
         * Must be overriden to define the Presenter used by this fragment.
         *
         * @return The presenter that will be used by this View.
         */
        protected abstract T createPresenter();

        @Override
        public void onDestroyView() {
                super.onDestroyView();

                if (this.presenter != null) {
                        this.presenter.detachView();
                }
        }

        @Override
        public void onDestroy() {
                super.onDestroy();

                this.presenter = null;
        }

//        // interface to handle the dialog click back to the Activity
//        public interface OnAlertDialogFragmentClickListener{
//                void onOkClicked(FeedbackDialog dialog);
//                void onCancelClicked(FeedbackDialog dialog);
//        }
//
//        public interface OnConfirmDialogFragmentClickListener{
//                void onOkClicked(FeedbackDialog dialog);
//                void onCancelClicked(FeedbackDialog dialog);
//        }

        private L mListener;

        public final L getListener() {
                return mListener;
        }

        protected abstract L setListener();

        @Override
        public void onAttach(Context context) {
                mListener = (L) setListener();
                fragmentCommunicator = (FragmentCommunicator) context;
                super.onAttach(context);
        }

        @Override
        public void onDetach() {
                super.onDetach();
                mListener = null;
                if (this.fragmentCommunicator != null) {
                        this.fragmentCommunicator = null;
                }
        }

}