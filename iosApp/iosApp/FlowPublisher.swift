//
//  PokemonPublisher.swift
//  iosApp
//
//  Created by Stephan Mantel on 05/01/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Combine
import shared

public struct FlowPublisher<Output : AnyObject>: Publisher {
    public typealias Failure = Never
    
    private let kotlinFlow: Kotlinx_coroutines_coreFlow
    
    public init(_ kotlinFlow:Kotlinx_coroutines_coreFlow) {
        self.kotlinFlow = kotlinFlow
    }

    public func receive<S: Subscriber>(subscriber: S) where S.Input == Output, S.Failure == Failure {
        let subscription = FlowSubscription(kotlinFlow, subscriber: subscriber)
        subscriber.receive(subscription: subscription)
    }
    
    final class FlowSubscription<S: Subscriber>: Subscription where S.Input == Output, S.Failure == Failure {
        private var subscriber: S?
        private var job: Kotlinx_coroutines_coreJob? = nil

        init(_ kotlinFlow: Kotlinx_coroutines_coreFlow, subscriber: S) {
            let iosFlow = KotlinNativeFlowWrapper<Output>(flow: kotlinFlow)
            
            self.subscriber = subscriber
    
            job = iosFlow.subscribe(
                scope: IOSScopeKt.iosScope,
                onEach: { position in
                    subscriber.receive(position!)
                },
                onComplete: { debugPrint("onComplete") },
                onThrow: { error in debugPrint(error) }
            )
        }
      
        func cancel() {
            subscriber = nil
            job?.cancel(cause: nil)
        }

        func request(_ demand: Subscribers.Demand) {}
    }
}
