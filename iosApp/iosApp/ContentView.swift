import UIKit
import SwiftUI
import sampleApp

struct ComposeView: UIViewControllerRepresentable {
    
    let rootCompoenent: UmbrellaRootComponent
    
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(root: rootCompoenent)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    
    let rootComponent: UmbrellaRootComponent
    
    var body: some View {
        ComposeView(rootCompoenent: rootComponent).ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}



