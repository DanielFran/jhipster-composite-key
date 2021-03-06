/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterTestModule } from '../../../test.module';
import { BusinessComponent } from '../../../../../../main/webapp/app/entities/business/business.component';
import { BusinessService } from '../../../../../../main/webapp/app/entities/business/business.service';
import { Business } from '../../../../../../main/webapp/app/entities/business/business.model';

describe('Component Tests', () => {

    describe('Business Management Component', () => {
        let comp: BusinessComponent;
        let fixture: ComponentFixture<BusinessComponent>;
        let service: BusinessService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [BusinessComponent],
                providers: [
                    BusinessService
                ]
            })
            .overrideTemplate(BusinessComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BusinessComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BusinessService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Business(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.businesses[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
