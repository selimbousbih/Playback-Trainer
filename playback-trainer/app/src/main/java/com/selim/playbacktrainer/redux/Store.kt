package com.selim.playbacktrainer.redux

typealias GetState<State> = () -> State
typealias Dispatcher = (Any) -> Any

interface Store<State> {
    val getState: GetState<State>
    var dispatch: Dispatcher
    val replaceReducer: (Reducer<State>) -> Unit
    val state: State
        get() = getState()
    val subscribe: (StoreSubscriber) -> StoreSubscription
}
