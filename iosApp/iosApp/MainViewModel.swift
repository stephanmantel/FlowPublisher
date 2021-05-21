//
//  MainViewModel.swift
//  iosApp
//
//  Created by Stephan Mantel on 06/01/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Combine
import shared

class MainViewModel: ObservableObject {
    
    @Published private (set) var name: String = "No pokemon"
    
    private var cancellable: AnyCancellable?
    
    private var publisher: FlowPublisher<Pokemon> = {
        let pokemonRepository: PokemonRepository = PokemonRepositoryImpl()
        return FlowPublisher(pokemonRepository.getNetworkPokemon())
    }()
    
    init() {
        cancellable = publisher
            .print()
            .map(\.name)
            .assign(to: \.name, on: self)
    }
}
