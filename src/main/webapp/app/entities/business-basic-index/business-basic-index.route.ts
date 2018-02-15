import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { BusinessBasicIndexComponent } from './business-basic-index.component';
import { BusinessBasicIndexDetailComponent } from './business-basic-index-detail.component';
import { BusinessBasicIndexPopupComponent } from './business-basic-index-dialog.component';
import { BusinessBasicIndexDeletePopupComponent } from './business-basic-index-delete-dialog.component';

export const businessBasicIndexRoute: Routes = [
    {
        path: 'business-basic-index',
        component: BusinessBasicIndexComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.businessBasicIndex.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'business-basic-index/:businessId/:basicIndexId/:year',
        component: BusinessBasicIndexDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.businessBasicIndex.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const businessBasicIndexPopupRoute: Routes = [
    {
        path: 'business-basic-index-new',
        component: BusinessBasicIndexPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.businessBasicIndex.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'business-basic-index/:businessId/:basicIndexId/:year/edit',
        component: BusinessBasicIndexPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.businessBasicIndex.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'business-basic-index/:businessId/:basicIndexId/:year/delete',
        component: BusinessBasicIndexDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.businessBasicIndex.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
