package baron.severin.io.dagger

import io.reactivex.Observable
import net.dean.jraw.RedditClient

class RedditObs(val get: Observable<RedditClient>)
