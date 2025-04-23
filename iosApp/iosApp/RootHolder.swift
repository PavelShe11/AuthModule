//
//  RootHolder.swift
//  iosApp
//
//  Created by Pavel on 23.04.2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import sampleApp

class RootHolder: ObservableObject {
    
    let rootComponentFactory = 
    
    let lifecycle: LifecycleRegistry
        let root: UmbrellaRootComponent

        init() {
            lifecycle = LifecycleRegistryKt.LifecycleRegistry()

            root = DefaultRootComponent(
                componentContext: DefaultComponentContext(lifecycle: lifecycle)
            )

            LifecycleRegistryExtKt.create(lifecycle)
        }

        deinit {
            // Destroy the root component before it is deallocated
            LifecycleRegistryExtKt.destroy(lifecycle)
        }
}
