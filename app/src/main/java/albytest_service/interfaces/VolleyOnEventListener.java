package albytest_service.interfaces;

public interface VolleyOnEventListener<T> {
    void onResponseGetEntity(T object);

    void onResponseSaveEntity(T object);
}
