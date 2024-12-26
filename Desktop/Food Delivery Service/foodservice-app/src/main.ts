import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { RestaurantListComponent } from './app/restaurant-list/restaurant-list.component';
bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));
