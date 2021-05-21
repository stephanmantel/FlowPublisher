import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject var viewModel = MainViewModel()
    
    var body: some View {
        Text(viewModel.name)
            .font(.system(size: 28))
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
