
package com.tincio.moviesapp.presentation.presenter;

import com.tincio.moviesapp.data.api.Constants;
import com.tincio.moviesapp.data.model.Result;
import com.tincio.moviesapp.domain.interactor.MovieInteractor;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class MoviePresenter extends Presenter<MoviePresenter.View> {

  private MovieInteractor interactor;

  public MoviePresenter(MovieInteractor interactor) {
    this.interactor = interactor;
  }

  public void onSearchMovie(String name) {
    getView().showLoading();
    Disposable disposable = interactor.searchMovie(Constants.TOKEN, name).subscribe(movies -> {
      if (!movies.getResults().isEmpty() && movies.getResults().size() > 0) {
        getView().hideLoading();
        getView().renderMovies(movies.getResults());
      } else {
        getView().showMovieNotFoundMessage();
      }
    }, Throwable::printStackTrace);

    addDisposableObserver(disposable);
  }

  public void getMovies() {
    getView().showLoading();
    Disposable disposable = interactor.getMovies(Constants.TOKEN, 1).subscribe(movies -> {
      if (!movies.getResults().isEmpty() && movies.getResults().size() > 0) {
        getView().hideLoading();
        getView().renderMovies(movies.getResults());
      } else {
        getView().showMovieNotFoundMessage();
      }
    }, Throwable::printStackTrace);

    addDisposableObserver(disposable);
  }

  public void launchMovieDetail(Result movie) {
    getView().launchMovieDetail(movie);
  }

  @Override
  public void terminate() {
    super.terminate();
    setView(null);
  }

  public interface View extends Presenter.View {

    void showLoading();

    void hideLoading();

    void showMovieNotFoundMessage();

    void showConnectionErrorMessage();

    void showServerError();

    void renderMovies(List<Result> movies);

    void launchMovieDetail(Result movie);
  }
}
