package com.app.motel.feature.room

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.app.motel.AppApplication
import com.app.motel.R
import com.app.motel.common.ultis.navigateFragmentWithSlide
import com.app.motel.core.AppBaseAdapter
import com.app.motel.core.AppBaseFragment
import com.app.motel.data.entity.PhongEntity
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Room
import com.app.motel.databinding.FragmentListRoomBinding
import com.app.motel.feature.room.viewmodel.RoomViewModel
import com.app.motel.ui.custom.CustomTabBar
import com.google.gson.Gson
import javax.inject.Inject

class RoomListFragment @Inject constructor() : AppBaseFragment<FragmentListRoomBinding>() {

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentListRoomBinding {
        return FragmentListRoomBinding.inflate(inflater, container, false)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel : RoomViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(RoomViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AppApplication).appComponent.inject(this)

        super.onViewCreated(view, savedInstanceState)
        init()
        listenStateViewModel()
    }

    lateinit var adapter: RoomAdapter

    private fun init() {
        viewModel.getRoom()

        adapter = RoomAdapter(object: AppBaseAdapter.AppListener<Room>(){
            override fun onClickItem(item: Room, action: AppBaseAdapter.ItemAction) {
                navigateFragmentWithSlide(R.id.roomDetailFragment, args = Bundle().apply { putString(RoomDetailFragment.ITEM_KEY, Gson().toJson(item)) })
            }
        })
        views.rcv.adapter = adapter
        views.txtSearch.setText(viewModel.liveData.searchText.value ?: "")
        views.tabBar.setOnTabSelectedListener(object: CustomTabBar.OnTabSelectedListener{
            override fun onTabSelected(position: Int) {
                when(position){
                    0 -> viewModel.liveData.currentRoomState.postValue(Resource.Success(data = null))
                    1 -> viewModel.liveData.currentRoomState.postValue(Resource.Success(data = PhongEntity.Status.RENTED))
                    2 -> viewModel.liveData.currentRoomState.postValue(Resource.Success(data = PhongEntity.Status.EMPTY))
                }
            }
        })
        views.tabBar.post {
            views.tabBar.setTabSelected(when(viewModel.liveData.currentRoomState.value?.data){
                PhongEntity.Status.RENTED -> 1
                PhongEntity.Status.EMPTY -> 2
                else -> 0
            })
        }
        views.txtSearch.addTextChangedListener {
            viewModel.liveData.searchText.postValue(views.txtSearch.text?.toString())
        }
        views.btnAdd.setOnClickListener{
            navigateFragmentWithSlide(R.id.roomFormFragment,)
        }
    }

    private fun listenStateViewModel() {
        viewModel.liveData.boardingRoom.observe(viewLifecycleOwner){
            if(it.isSuccess()){
                val rooms = viewModel.liveData.roomsWithCurrentStateSearch
                adapter.updateData(rooms)
                views.tvEmpty.isVisible = rooms.isEmpty()
            }
        }
        viewModel.liveData.currentRoomState.observe(viewLifecycleOwner){
            if(it.isSuccess()){
                val rooms = viewModel.liveData.roomsWithCurrentStateSearch
                adapter.updateData(rooms)
                views.tvEmpty.isVisible = rooms.isEmpty()
            }
        }
        viewModel.liveData.searchText.observe(viewLifecycleOwner){
            Log.d("RoomListFragment", "searchText: $it")
            val rooms = viewModel.liveData.roomsWithCurrentStateSearch
            adapter.updateData(rooms)
            views.tvEmpty.isVisible = rooms.isEmpty()
        }
    }
}