import SwiftUI
import shared
import SwiftUI

struct ComposeView: UIViewControllerRepresentable {
    let safeAreaInsetsBottom: CGFloat
        let safeAreaInsetsTop: CGFloat

    func makeUIViewController(context: Context) -> UIViewController {
        ApplicationKt.Application()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        
        GeometryReader { geometry in
            VStack {
                ComposeView(safeAreaInsetsBottom: geometry.safeAreaInsets.bottom, safeAreaInsetsTop: geometry.safeAreaInsets.top)
//                    .padding(.top, 20)
//                    .padding(.bottom, geometry.safeAreaInsets.bottom)
            }
        }
//         .edgesIgnoringSafeArea(.top)
        .edgesIgnoringSafeArea(.bottom)
            
    }
}
