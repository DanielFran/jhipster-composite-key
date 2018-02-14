import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { BusinessBasicIndex } from './business-basic-index.model';
import { BusinessBasicIndexService } from './business-basic-index.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-business-basic-index',
    templateUrl: './business-basic-index.component.html'
})
export class BusinessBasicIndexComponent implements OnInit, OnDestroy {
businessBasicIndices: BusinessBasicIndex[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private businessBasicIndexService: BusinessBasicIndexService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.businessBasicIndexService.query().subscribe(
            (res: HttpResponse<BusinessBasicIndex[]>) => {
                this.businessBasicIndices = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInBusinessBasicIndices();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: BusinessBasicIndex) {
        return item.id;
    }
    registerChangeInBusinessBasicIndices() {
        this.eventSubscriber = this.eventManager.subscribe('businessBasicIndexListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
