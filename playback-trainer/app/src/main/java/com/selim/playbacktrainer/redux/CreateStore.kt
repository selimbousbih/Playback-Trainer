package com.selim.playbacktrainer.redux


typealias StoreSubscriber = () -> Unit
typealias StoreSubscription = () -> Unit

fun <State> createStore(
    reducer: Reducer<State>,
    preloadedState: State,
): Store<State> {
    var currentListeners = mutableListOf<() -> Unit>()
    var nextListeners = currentListeners

    fun ensureCanMutateNextListeners() {
        if (nextListeners === currentListeners) {
            nextListeners = currentListeners.toMutableList()
        }
    }

    val currentReducer = reducer
    var currentState = preloadedState
    var isDispatching = false

    fun getState(): State {
        check(!isDispatching) {
            """|You may not call store.getState() while the reducer is executing.
             |The reducer has already received the state as an argument.
             |Pass it down from the top reducer instead of reading it from the 
             |store.
             |You may be accessing getState while dispatching from another
             |thread.  Try createThreadSafeStore().
             |https://reduxkotlin.org/introduction/threading""".trimMargin()
        }

        return currentState
    }

    fun subscribe(listener: StoreSubscriber): StoreSubscription {
        check(!isDispatching) {
            """|You may not call store.subscribe() while the reducer is executing.
             |If you would like to be notified after the store has been updated, 
             |subscribe from a component and invoke store.getState() in the 
             |callback to access the latest state. See 
             |https://www.reduxkotlin.org/api/store#subscribelistener-storesubscriber
             |for more details.
             |You may be seeing this due accessing the store from multiplethreads.
             |Try createThreadSafeStore()
             |https://reduxkotlin.org/introduction/threading""".trimMargin()
        }

        var isSubscribed = true

        ensureCanMutateNextListeners()
        nextListeners.add(listener)

        return {
            if (!isSubscribed) {
                Unit
            }

            check(!isDispatching) {
                """You may not unsubscribe from a store listener while the reducer
                 |is executing. See 
                 |https://www.reduxkotlin.org/api/store#subscribelistener-storesubscriber
                 |for more details.""".trimMargin()
            }

            isSubscribed = false

            ensureCanMutateNextListeners()
            val index = nextListeners.indexOf(listener)
            nextListeners.removeAt(index)
        }
    }

    fun dispatch(action: Any): Any {
        check(!isDispatching) {
            """You may not dispatch while state is being reduced.
            |2 conditions can cause this error:
            |    1) Dispatching from a reducer
            |    2) Dispatching from multiple threads
            |If #2 switch to createThreadSafeStore().
            |https://reduxkotlin.org/introduction/threading""".trimMargin()
        }

        try {
            isDispatching = true
            currentState = currentReducer(currentState, action)
        } finally {
            isDispatching = false
        }

        val listeners = nextListeners
        currentListeners = nextListeners
        listeners.forEach { it() }

        return action
    }

    return object : Store<State> {
        override val getState = ::getState
        override var dispatch: Dispatcher = ::dispatch
        override var subscribe = ::subscribe
        override val replaceReducer: (Reducer<State>) -> Unit = { }
    }
}
