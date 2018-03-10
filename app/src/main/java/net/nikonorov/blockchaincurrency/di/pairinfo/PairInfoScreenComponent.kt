package net.nikonorov.blockchaincurrency.di.pairinfo

import dagger.Subcomponent
import net.nikonorov.blockchaincurrency.presentation.info.view.PairInfoFragment

/**
 * Created by Vitaly Nikonorov on 10.03.2018.
 * email@nikonorov.net
 */
@Subcomponent(modules = [(PairInfoModule::class)])
@PairInfoScreenScope
interface PairInfoScreenComponent {
    fun inject(pairInfoFragment: PairInfoFragment)
}
