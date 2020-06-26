package com.prudhvir3ddy.dailybugle.ui

import com.prudhvir3ddy.dailybugle.database.data.UIDatabaseArticles

interface CustomClickListener {
  fun cardClicked(f: UIDatabaseArticles?)
}