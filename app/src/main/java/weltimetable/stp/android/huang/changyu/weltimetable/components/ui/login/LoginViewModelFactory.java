package weltimetable.stp.android.huang.changyu.weltimetable.components.ui.login;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import weltimetable.stp.android.huang.changyu.weltimetable.components.data.LoginDataSource;
import weltimetable.stp.android.huang.changyu.weltimetable.components.data.LoginRepository;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource(),null));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
