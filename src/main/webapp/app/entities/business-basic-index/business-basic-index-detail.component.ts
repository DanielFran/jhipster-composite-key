import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { BusinessBasicIndex } from './business-basic-index.model';
import { BusinessBasicIndexService } from './business-basic-index.service';

@Component({
    selector: 'jhi-business-basic-index-detail',
    templateUrl: './business-basic-index-detail.component.html'
})
export class BusinessBasicIndexDetailComponent implements OnInit, OnDestroy {

    businessBasicIndex: BusinessBasicIndex;
    private subscription: Subscription;
    private eventSubscriber: Subscription;
    private disabledId: boolean;

    constructor(
        private eventManager: JhiEventManager,
        private businessBasicIndexService: BusinessBasicIndexService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['businessId'], params['basicIndexId'], params['year']);
            this.disabledId = (params['businessId'] && params['basicIndexId'] && params['year']);
        });
        this.registerChangeInBusinessBasicIndices();
    }

    load(businessId: number, basicIndexId: number, year: number) {
        this.businessBasicIndexService.find(businessId, basicIndexId, year)
            .subscribe((businessBasicIndexResponse: HttpResponse<BusinessBasicIndex>) => {
                this.businessBasicIndex = businessBasicIndexResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInBusinessBasicIndices() {
        this.eventSubscriber = this.eventManager.subscribe(
            'businessBasicIndexListModification',
            (response) => this.load(this.businessBasicIndex.businessId, this.businessBasicIndex.basicIndexId, this.businessBasicIndex.year)
        );
    }
}
