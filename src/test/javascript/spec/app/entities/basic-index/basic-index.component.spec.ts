/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterTestModule } from '../../../test.module';
import { BasicIndexComponent } from '../../../../../../main/webapp/app/entities/basic-index/basic-index.component';
import { BasicIndexService } from '../../../../../../main/webapp/app/entities/basic-index/basic-index.service';
import { BasicIndex } from '../../../../../../main/webapp/app/entities/basic-index/basic-index.model';

describe('Component Tests', () => {

    describe('BasicIndex Management Component', () => {
        let comp: BasicIndexComponent;
        let fixture: ComponentFixture<BasicIndexComponent>;
        let service: BasicIndexService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [BasicIndexComponent],
                providers: [
                    BasicIndexService
                ]
            })
            .overrideTemplate(BasicIndexComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BasicIndexComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BasicIndexService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new BasicIndex(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.basicIndices[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
