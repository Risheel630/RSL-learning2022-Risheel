// Generated by view binder compiler. Do not edit!
package com.example.quizapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.quizapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class QuestionViewBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView question;

  @NonNull
  public final RadioButton radio0;

  @NonNull
  public final RadioButton radio1;

  @NonNull
  public final RadioButton radio2;

  @NonNull
  public final RadioButton radio3;

  @NonNull
  public final RadioGroup radioGroup1;

  private QuestionViewBinding(@NonNull LinearLayout rootView, @NonNull TextView question,
      @NonNull RadioButton radio0, @NonNull RadioButton radio1, @NonNull RadioButton radio2,
      @NonNull RadioButton radio3, @NonNull RadioGroup radioGroup1) {
    this.rootView = rootView;
    this.question = question;
    this.radio0 = radio0;
    this.radio1 = radio1;
    this.radio2 = radio2;
    this.radio3 = radio3;
    this.radioGroup1 = radioGroup1;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static QuestionViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static QuestionViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.question_view, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static QuestionViewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.question;
      TextView question = ViewBindings.findChildViewById(rootView, id);
      if (question == null) {
        break missingId;
      }

      id = R.id.radio0;
      RadioButton radio0 = ViewBindings.findChildViewById(rootView, id);
      if (radio0 == null) {
        break missingId;
      }

      id = R.id.radio1;
      RadioButton radio1 = ViewBindings.findChildViewById(rootView, id);
      if (radio1 == null) {
        break missingId;
      }

      id = R.id.radio2;
      RadioButton radio2 = ViewBindings.findChildViewById(rootView, id);
      if (radio2 == null) {
        break missingId;
      }

      id = R.id.radio3;
      RadioButton radio3 = ViewBindings.findChildViewById(rootView, id);
      if (radio3 == null) {
        break missingId;
      }

      id = R.id.radioGroup1;
      RadioGroup radioGroup1 = ViewBindings.findChildViewById(rootView, id);
      if (radioGroup1 == null) {
        break missingId;
      }

      return new QuestionViewBinding((LinearLayout) rootView, question, radio0, radio1, radio2,
          radio3, radioGroup1);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
