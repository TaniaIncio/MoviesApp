
package com.tincio.moviesapp.presentation.presenter;

import com.tincio.moviesapp.data.api.Constants;
import com.tincio.moviesapp.data.api.client.MovieClient;
import com.tincio.moviesapp.data.model.Response;
import com.tincio.moviesapp.domain.interactor.DetailMovieInteractor;

import io.reactivex.disposables.Disposable;

public class DetailMoviePresenter extends Presenter<DetailMoviePresenter.View> {

  private DetailMovieInteractor interactor;

  public DetailMoviePresenter() {
    interactor  = new DetailMovieInteractor(new MovieClient());
  }


  public void getMovieId(Integer id) {
    getView().showLoading();
    Disposable disposable = interactor.getMovieId(Constants.TOKEN, id).subscribe(movie -> {
      if (movie != null) {
        getView().hideLoading();
        getView().showMovie(movie);
      }
    }, Throwable::printStackTrace);

    addDisposableObserver(disposable);
  }
  @Override
  public void terminate() {
    super.terminate();
    setView(null);
  }

  public interface View extends Presenter.View {

    void showLoading();

    void hideLoading();

    void showMovie(Response movie);

  }
}
