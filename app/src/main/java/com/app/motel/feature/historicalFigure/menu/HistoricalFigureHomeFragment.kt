package com.app.motel.feature.historicalFigure.menu

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.app.motel.data.model.Section
import com.app.motel.feature.historicalFigure.viewmodel.HistoricalFigureViewModel
import com.app.motel.ui.show
import com.google.android.material.divider.MaterialDivider
import com.history.vietnam.AppApplication
import com.history.vietnam.R
import com.history.vietnam.core.AppBaseFragment
import com.history.vietnam.databinding.FragmentHistorycalFigureHomeBinding
import com.history.vietnam.ui.showToast
import javax.inject.Inject
import kotlin.random.Random

class HistoricalFigureHomeFragment : AppBaseFragment<FragmentHistorycalFigureHomeBinding>() {

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHistorycalFigureHomeBinding {
        return FragmentHistorycalFigureHomeBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : HistoricalFigureViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(HistoricalFigureViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)


        init()
        listenStateViewModel()
    }

    private fun init() {

    }

    private fun listenStateViewModel() {
        viewModel.liveData.figure.observe(viewLifecycleOwner){
            setUpInformationView()
        }
        viewModel.liveData.figureSummary.observe(viewLifecycleOwner){
            views.imgOriginImage.show(it?.data?.originalImage?.source)
            views.tvName.text = it?.data?.titles?.normalized
            views.tvDesc.text = it?.data?.description

        }
        viewModel.liveData.figureDetail.observe(viewLifecycleOwner){
            setupContentWiki()
        }
        viewModel.liveData.selectContent.observe(viewLifecycleOwner){
            val safePosition = it.coerceIn(0, sectionTextViewList.size)

            if(safePosition == 0){
                views.scrollView.smoothScrollTo(0, 0, 500)
                return@observe
            }

            val sectionView = sectionTextViewList[safePosition - 1]

            val screenHeight = Resources.getSystem().displayMetrics.heightPixels
            val toOffset = sectionView.top + (screenHeight * 0.75).toInt()

            views.scrollView.smoothScrollTo(0, toOffset, 500)
        }

        // custom text
        viewModel.settingRepository.state.textSize.observe(viewLifecycleOwner){
            views.tvDesc.textSize = it.toFloat()

            setUpInformationView()
            setupContentWiki()
        }

        viewModel.settingRepository.state.textFont.observe(viewLifecycleOwner){
            val fontTypeface = viewModel.settingRepository.state.getTextFont(requireContext())
            views.tvDesc.typeface  = fontTypeface

            setUpInformationView()
            setupContentWiki()
        }
        viewModel.settingRepository.state.backgroundColor.observe(viewLifecycleOwner){
            val textColor = viewModel.settingRepository.state.getTextColor(requireContext())
            val hintTextColor = viewModel.settingRepository.state.getHintTextColor(requireContext())

            views.tvName.setTextColor(textColor)
            views.tvDesc.setTextColor(hintTextColor)

            setUpInformationView()
            setupContentWiki()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpInformationView(){
        val figure = viewModel.liveData.figure.value

        views.lyInformationList.removeAllViews()
        val textSize = viewModel.settingRepository.state.getTextSize(requireContext())
        val fontTypeface = viewModel.settingRepository.state.getTextFont(requireContext())
        val textColor = viewModel.settingRepository.state.getTextColor(requireContext())
        val hintTextColor = viewModel.settingRepository.state.getHintTextColor(requireContext())

        val infoMap: Map<String, String?> = mapOf(
            "Sinh" to figure?.birthYear,
            "Mất" to figure?.deathDate,
            "Vợ" to figure?.spouse,
            "Tước hiệu" to figure?.title,
            "Triều đại" to figure?.dynasty
        )

        infoMap.forEach { (key, value) ->
            val itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_info_row, views.root, false)

            val keyText = itemView.findViewById<TextView>(R.id.keyText)
            val valueText = itemView.findViewById<TextView>(R.id.valueText)

            val isEmpty = value.isNullOrEmpty()

            keyText.text = "• $key"
            valueText.text = if(isEmpty) "Không rõ" else value

            keyText.textSize = textSize
            valueText.textSize = textSize
            keyText.typeface = fontTypeface
            valueText.typeface = fontTypeface
            valueText.setTextColor(textColor)

            if(isEmpty){
                valueText.setTextColor(hintTextColor)
            } else if (value?.contains("Xem") == true || value?.contains("Nhà") == true) {
                valueText.setTextColor(Color.BLUE)
                valueText.paint.isUnderlineText = true
            }

            views.lyInformationList.addView(itemView)
        }
    }

    private val sectionTextViewList = mutableListOf<View>()
    private fun setupContentWiki(){
        val sections: List<Section> = viewModel.liveData.figureContentSections

        views.lyContent.removeAllViews()
        sectionTextViewList.clear()
        val currentTextSize = viewModel.settingRepository.state.getTextSize(requireContext())
        val fontTypeface = viewModel.settingRepository.state.getTextFont(requireContext())
        val textColor = viewModel.settingRepository.state.getTextColor(requireContext())

        sections.forEachIndexed { index, section ->

            var divider: MaterialDivider? = null

            val title = TextView(requireContext()).apply {
                text = section.title
                when{
                    section.isLevel1 -> {
                        textSize = 20f
                        setPadding(16, 32, 16, 0)
                        typeface = fontTypeface
                        setTextColor(textColor)
                        divider = MaterialDivider(requireContext())
                    }
                    section.isLevel2 -> {
                        textSize = 18f
                        setTypeface(null, Typeface.BOLD)
                        setTextColor(textColor)
                        typeface = fontTypeface
                        setPadding(16, 8, 16, 8)
                    }
                    section.isLevel3 -> {
                        setTypeface(null, Typeface.BOLD)
                        textSize = 15f
                        setTextColor(textColor)
                        typeface = fontTypeface
                        setPadding(16, 8, 16, 8)
                    }
                }

                id = View.generateViewId()
                tag = index // match with timeline
            }

            val body = TextView(requireContext()).apply {
                text = section.content
                textSize = currentTextSize
                setTextColor(textColor)
                typeface = fontTypeface
                setPadding(16, 0, 16, 32)
            }

            views.lyContent.addView(title)
            divider?.apply { views.lyContent.addView(this) }
            views.lyContent.addView(body)

            sectionTextViewList.add(title)
        }
    }
}