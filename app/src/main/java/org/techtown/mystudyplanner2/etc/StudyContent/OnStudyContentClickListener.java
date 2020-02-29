package org.techtown.mystudyplanner2.etc.StudyContent;

import android.view.View;

public interface OnStudyContentClickListener {
    public void onItemClick(StudyContentAdapter.ViewHolder holder, View view, int position);
}
