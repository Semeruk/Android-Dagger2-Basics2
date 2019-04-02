package com.semeruk.android_dagger2.scope;

import javax.inject.Scope;

// If we wish to use a component created for the
// entire lifecycle of a user session signed into the application,
// we can define our own UserScope interface
@Scope
public @interface UserScope {
}
