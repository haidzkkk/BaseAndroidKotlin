package com.app.langking.feature.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.app.langking.R
import com.app.langking.data.model.SettingItemLearn
import com.app.langking.data.model.Speak
import com.app.langking.data.model.TypeSpeak
import com.app.langking.data.model.Word
import com.app.langking.databinding.ItemLearnBinding
import java.util.Locale

class LearnWordAdapter(
    private val onItemClick: (Word) -> Unit,
    private val onSpeech: (Speak) -> Unit,
) : RecyclerView.Adapter<LearnWordAdapter.ViewHolder>() {

    private var words: List<Word> = arrayListOf()
    private var settingItems: MutableMap<Int, SettingItemLearn> = hashMapOf()

    @SuppressLint("NotifyDataSetChanged")
    fun populateData(words: List<Word>?) {
        if(words == null) return;
        this.words = words
        settingItems = this.words.indices.associateWith { SettingItemLearn() }.toMutableMap()
        notifyDataSetChanged()
    }

    var currentPosition: Int = -1
    @SuppressLint("NotifyDataSetChanged")
    fun changeItemSelected(position: Int){
        if(currentPosition == position) return
        currentPosition = position
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLearnBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = words.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(words[position], position)
    }

    inner class ViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var settingItem: SettingItemLearn
        private lateinit var word: Word
        private var position: Int = -1

        fun bind(wordBind: Word, positionBind: Int) {
            word = wordBind
            position = positionBind

            settingItem = settingItems.getOrPut(positionBind) { SettingItemLearn() }

            with(binding as ItemLearnBinding){

                handleChangeColorLayoutHeader(this, position == currentPosition)

                binding.tvPosition.text = "${position + 1}."
                binding.tvEnglish.text = word.english
                binding.tvPronunciation.text = word.pronunciation
                binding.tvVietnamese.text = word.vietnamese
                binding.tvDescEnglish.text = word.description
                binding.tvDescVietnamese.text = word.descriptionVietnamese

                handleExpandDesc(this)
                icExpand.setOnClickListener {
                    settingItem.isExpanded = !settingItem.isExpanded
                    handleExpandDesc(this)
                }
                lyBottom.setOnClickListener {
                    settingItem.isExpanded = !settingItem.isExpanded
                    handleExpandDesc(this)
                }
                binding.icVolume.setOnClickListener {
                    onSpeech(Speak(
                        word = word,
                        typeSpeak = TypeSpeak(isEnglish = true))
                    )
                }
                binding.icVolumeSlow.setOnClickListener {
                    onSpeech(Speak(
                        word,
                        speed = 0.3f,
                        typeSpeak = TypeSpeak(isEnglish = true)
                    ))
                }
                binding.icVolumeVietnamese.setOnClickListener {
                    onSpeech(Speak(
                        word,
                        voice = Speak.voiceVietnamese,
                        typeSpeak = TypeSpeak(isVietnamese = true)
                    ))
                }
                binding.icPlay.setOnClickListener {
                    onSpeech(Speak(word,
                        durationMillisecond = 1000,
                        typeSpeak = TypeSpeak(
                            isEnglish = true,
                            isEnglishSlow = true,
                            isVietnamese = true,
                            isEnglishDesc = true,
                            isVietnameseDesc = true
                        )
                    ))
                    changeItemSelected(position)
                }
                lyHeader.setOnClickListener {
                    settingItem.isExpanded = true
                    handleExpandDesc(this)
                    onItemClick(word)
                }
                handleHideTextView(true, binding.tvEnglish, binding.iconShow)
                handleHideTextView(false, binding.tvVietnamese, binding.iconVietnameseShow)
                binding.iconShow.setOnClickListener {
                    settingItem.isHideEnglish = !settingItem.isHideEnglish
                    handleHideTextView(true, binding.tvEnglish, binding.iconShow)
                }
                binding.iconVietnameseShow.setOnClickListener {
                    settingItem.isHideVietnamese = !settingItem.isHideVietnamese
                    handleHideTextView(false, binding.tvVietnamese, binding.iconVietnameseShow)
                }
            }
        }

        @SuppressLint("ResourceAsColor")
        private fun handleChangeColorLayoutHeader(binding: ItemLearnBinding, select: Boolean) {val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.background_border_radius_top_background3) as LayerDrawable
            val backgroundLayer = drawable.getDrawable(0) as GradientDrawable

            val color = ContextCompat.getColor(binding.root.context, if (select) R.color.primaryVariant else R.color.background3)
            backgroundLayer.setColor(color)

            binding.lyHeader.background = drawable

        }

        private fun handleExpandDesc(binding: ItemLearnBinding){
            if(word.descriptionVietnamese == null || word.descriptionVietnamese!!.isEmpty()){
                binding.lyDesc.isVisible = false
                binding.icExpand.isVisible = false
                return
            }

            binding.icExpand.isVisible = true
            binding.lyDesc.isVisible = settingItem.isExpanded
            binding.icExpand.setImageResource(if (settingItem.isExpanded) R.drawable.icons8_up else R.drawable.icons8_down)
        }

        private fun handleHideTextView(isEnglish: Boolean, textView: TextView, imageView: ImageView){
            textView.text = word.let{word -> if(isEnglish) word.english else word.vietnamese}.let{
                if (settingItem.let {item -> if(isEnglish) item.isHideEnglish else item.isHideVietnamese }) "_".repeat(it?.length ?: 0)
                else it
            }
            imageView.setImageResource(if (settingItem.let {item -> if(isEnglish) item.isHideEnglish else item.isHideVietnamese }) R.drawable.icons8_hide else R.drawable.icons8_show)
        }
    }

}
