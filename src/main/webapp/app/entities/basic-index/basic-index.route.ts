import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { BasicIndexComponent } from './basic-index.component';
import { BasicIndexDetailComponent } from './basic-index-detail.component';
import { BasicIndexPopupComponent } from './basic-index-dialog.component';
import { BasicIndexDeletePopupComponent } from './basic-index-delete-dialog.component';

export const basicIndexRoute: Routes = [
    {
        path: 'basic-index',
        component: BasicIndexComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.basicIndex.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'basic-index/:id',
        component: BasicIndexDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.basicIndex.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const basicIndexPopupRoute: Routes = [
    {
        path: 'basic-index-new',
        component: BasicIndexPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.basicIndex.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'basic-index/:id/edit',
        component: BasicIndexPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.basicIndex.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'basic-index/:id/delete',
        component: BasicIndexDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterApp.basicIndex.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
