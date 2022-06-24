package kozlov.artyom.movie.presentation.recycler

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class SpaceItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position: Int = parent.getChildAdapterPosition(view)
        val isLast = position == state.itemCount - 1
        if (isLast) {
            outRect.top = 30
        }
        if (position == 0) {
            if (!isLast) outRect.bottom = 30
            super.getItemOffsets(outRect, view, parent, state)
        }
    }
}




