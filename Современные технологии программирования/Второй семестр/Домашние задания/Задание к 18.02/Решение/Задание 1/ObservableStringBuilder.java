package com.company;

/**
 * Написать класс StringBuilder с оповещением других объектов-слушателей об изменении своего состояния.
 * Делегировать стандартные методы стандартному StringBuilder. Паттерн «Наблюдатель».
 */
class ObservableStringBuilder {

    private StringBuilder delegateBuilder;
    private OnStringChangedListener onChangeListener;

    ObservableStringBuilder() {
        delegateBuilder = new StringBuilder();
    }

    ObservableStringBuilder(String string) {
        delegateBuilder = new StringBuilder(string);
    }

    void setOnChangeListener(OnStringChangedListener onChangeListener) {
        this.onChangeListener = onChangeListener;
    }

    ObservableStringBuilder append(Object obj) {
        delegateBuilder.append(obj);
        notifyListeners();
        return this;
    }

    public ObservableStringBuilder insert(int index, char[] str, int offset, int len) {
        delegateBuilder.insert(index, str, offset, len);
        notifyListeners();
        return this;
    }

    public ObservableStringBuilder replace(int start, int end, String str) {
        delegateBuilder.replace(start, end, str);
        notifyListeners();
        return this;
    }

    public ObservableStringBuilder delete(int start, int day) {
        delegateBuilder.delete(start, day);
        notifyListeners();
        return this;
    }

    private void notifyListeners() {
        if (onChangeListener != null)
            onChangeListener.onStringChanged(this);
    }

    @Override
    public String toString() {
        return delegateBuilder.toString();
    }
}
