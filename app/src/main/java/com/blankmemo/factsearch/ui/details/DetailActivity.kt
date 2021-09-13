package com.blankmemo.factsearch.ui.details


//@AndroidEntryPoint
//class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {
//
//  @Inject
//  lateinit var detailViewModelFactory: DetailViewModel.AssistedFactory
//
//  @VisibleForTesting
//  val viewModel: DetailViewModel by viewModels {
//    DetailViewModel.provideFactory(detailViewModelFactory, pokemonItem.name)
//  }
//
//  private val pokemonItem: Pokemon by bundleNonNull(EXTRA_POKEMON)
//
//  override fun onCreate(savedInstanceState: Bundle?) {
//    onTransformationEndContainerApplyParams()
//    super.onCreate(savedInstanceState)
//    binding {
//      pokemon = pokemonItem
//      vm = viewModel
//    }
//  }
//
//  companion object {
//    @VisibleForTesting
//    const val EXTRA_POKEMON = "EXTRA_POKEMON"
//
//    fun startActivity(transformationLayout: TransformationLayout, pokemon: Pokemon) =
//      transformationLayout.context.intentOf<DetailActivity> {
//        putExtra(EXTRA_POKEMON to pokemon)
//        TransformationCompat.startActivity(transformationLayout, intent)
//      }
//  }
//}
