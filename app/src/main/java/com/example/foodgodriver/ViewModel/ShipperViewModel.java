package com.example.foodgodriver.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.foodgodriver.Network.DTO.AvailabilityResponse;
import com.example.foodgodriver.Network.Repository.ShipperRepository;
import com.example.foodgodriver.Utils.Result;

public class ShipperViewModel extends AndroidViewModel {
    private final ShipperRepository shipperRepository;

    // Constructor
    public ShipperViewModel(@NonNull Application application) {
        super(application);
        // Khởi tạo Repository và truyền vào Context của Application
        shipperRepository = new ShipperRepository(application);
    }

    // ... (các hàm ViewModel khác của bạn)

    /**
     * Hàm này được gọi từ Activity/Fragment để cập nhật trạng thái
     * @param isAvailable true = Sẵn sàng, false = Tạm nghỉ
     * @return LiveData chứa kết quả
     */
    public LiveData<Result<AvailabilityResponse>> updateAvailability(boolean isAvailable) {
        return shipperRepository.updateAvailability(isAvailable);
    }
}
