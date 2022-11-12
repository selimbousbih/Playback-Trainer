package com.selim.playbacktrainer.redux

import com.selim.playbacktrainer.redux.AppState.*

typealias Reducer<State> = (state: State, action: Any) -> State

enum class Action {
    FinishRecord, StartRecord, Play, StopPlay
}

val reducer: Reducer<AppState> = { state, action ->
    when (action) {
        Action.FinishRecord -> FinishRecordingState
        Action.StartRecord -> RecordingState
        Action.Play -> PlayingState
        Action.StopPlay -> IdleState
        else -> state
    }
}